package com.quanmin.paresfile;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.quanmin.paresfile.utils.TestUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class CodeGenerator extends AutoGenerator {

    /**
     * 继承自AutoGenerator，复写了execute方法，将controller的path设置为null，在我们的这部分框架代码中，
     * 避免自动生成controller类。
     */
    @Override
    public void execute() {
        log.debug("==========================准备生成文件...==========================");
        PackageConfig packageInfo = this.getPackageInfo();
        DataSourceConfig dataSource = this.getDataSource();
        StrategyConfig strategy = this.getStrategy();
        TemplateConfig template = this.getTemplate();
        GlobalConfig globalConfig = this.getGlobalConfig();
        AbstractTemplateEngine templateEngine = this.getTemplateEngine();

        // 初始化配置
        if (null == config) {
            config = new ConfigBuilder(packageInfo, dataSource, strategy, template, globalConfig);

            List<TableInfo> tableInfoList = config.getTableInfoList();
            if (tableInfoList.size() <= 0) {
                System.out.println("Table(s) " + Arrays.toString(strategy.getInclude()) + " not exist!");
                return;
            }


            // 设置所有的entity都展示@TableName注解，展示是哪张数据库表
            for (TableInfo tableInfo : tableInfoList) {
                tableInfo.setConvert(true);
                boolean isDone = false;
                do {
                    System.out.println("[INFO] Entity: " + tableInfo.getEntityName() + ",  Mapper: " + tableInfo.getMapperName() + ", Service: " + tableInfo.getServiceName());
                    String yes = TestUtils.scanner("Generate codes with options above? (Y/N)");

                    if (yes.equals("Y")) {
                        isDone = true;
                    } else {
                        String entity = TestUtils.scanner("Entity ");
                        String mapper = entity + "Mapper";
                        String service = "I" + entity + "Service";
                        String serviceImpl = entity + "ServiceImpl";

                        tableInfo.setControllerName(entity + "Controller");
                        tableInfo.setEntityName(entity);
                        tableInfo.setMapperName(mapper);
                        tableInfo.setServiceName(service);
                        tableInfo.setServiceImplName(serviceImpl);
                    }
                } while (!isDone);
            }

            config.getPathInfo().forEach((key, value) -> {
                if (value != null) {
                    File dir = new File(value);
                    if (!dir.exists()) {
                        boolean result = dir.mkdirs();
                        if (result) {
                            log.debug("创建目录： [" + value + "]");
                        }
                    }
                }
            });

            if (null != injectionConfig) {
                injectionConfig.setConfig(config);
            }
        }

        if (null == templateEngine) {
            // 为了兼容之前逻辑，采用 Velocity 引擎 【 默认 】
            templateEngine = new VelocityTemplateEngine();
        }

        // 模板引擎初始化执行文件输出
        ConfigBuilder configBuilder = this.pretreatmentConfigBuilder(config);
        templateEngine.init(configBuilder).batchOutput().open();
        log.debug("==========================文件生成完成！！！==========================");
    }

    private static void generate(String moduleName, String tableName) {

        String tablePrefix = tableName.substring(0, tableName.indexOf('_'));

        // 代码生成器
        CodeGenerator mpg = new CodeGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Yangquanmin");
        gc.setOpen(false);
        gc.setEnableCache(false);
        gc.setSwagger2(true);
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/t_test?useUnicode=true&&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(GlobalConstants.BASE_PACKAGE_NAME + ".modules");
        mpg.setPackageInfo(pc);


        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(tablePrefix);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        // 公共父类
        //strategy.setSuperControllerClass("com.baomidou.ant.core.BaseController");

        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tableName.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

    private static void generateFromInput() {
        String moduleName = TestUtils.scanner("Module name");
        String tableName = TestUtils.scanner("Table name");

        generate(moduleName, tableName);
    }

    public static void main(String[] args) {
        generateFromInput();
    }
}
