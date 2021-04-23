package com.arc.code.generator.config.properties.impl;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import static com.arc.code.generator.controller.data.GenerateCodeV1Controller.openOutputDir;

/**
 * 代码生成器环境上下文
 *
 * @author may
 * @since 2021/4/16 18:19
 */
@Data
public class ArcCodeGeneratorContext implements ArcPropertiesProvider {

    private String url; //数据库连接url
    private String username;  //数据库账号
    private String password; //数据库密码
    private String driverClassName; //驱动名称

    @Deprecated
    private String schemaName;//schemaName(数据库名)【必要】


    private String tableName;//表名
    private String tableAlias;//数据库表在mapper的sql中的别名
    private boolean onlyModelMapperAndXml;

    //输出配置
    private String rootNamespace = "com.arc.zero";
    private String mapperNamespace = "com.arc.zero.mapper.shop";
    private String modelNamespace = "com.arc.core.model.domain.shop";
    private String serviceNamespace = "com.arc.core.model.domain.shop";
    private String serviceImplNamespace = "com.arc.core.model.domain.shop";
    private String controllerNamespace = "com.arc.core.model.domain.shop";


    private String output = File.separator + "output_";// //T:\data\log\

    private String author;//"@author" 需要指定,缺省情况下获取机器当前用户

    @Override
    public String getOutput() {
             return output;
    }

    @Override
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * 要多行注释还单行注释
     * multiline comment 多行注释
     * End-of-line comment  行尾注释
     */
    private boolean commentFormatAsEndOfLine = false;//End-of-line comment  行尾注释

    @Deprecated
    private boolean onlyEnableEndOfLineCommentAndDisableMultilineComment = false;

    private boolean success;

    private boolean useProjectDefaultDataSource;// true=使用项目默认的数据源/false=使用用户指定的数据源

    private boolean scanAllTable=false;// true=使用项目默认的数据源/false=使用用户指定的数据源

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public boolean isUseProjectDefaultDataSource() {
        return useProjectDefaultDataSource;
    }

    @Override
    public void setUseProjectDefaultDataSource(boolean useProjectDefaultDataSource) {
        this.useProjectDefaultDataSource = useProjectDefaultDataSource;
    }

    @Override  public boolean isScanAllTable() {
        return scanAllTable;
    }

    @Override public void setScanAllTable(boolean scanAllTable) {
        this.scanAllTable = scanAllTable;
    }
    //数据库链接配置
    //数据库连接url
//    {
//
//        //8.0   "com.mysql.cj.jdbc.Driver"
//        url = "jdbc:mysql://122.51.110.127:3306/zero?useUnicode=true&characterEncoding=UTF-8&useAffectedRows=true&useSSL=false&serverTimezone=GMT%2B8";
//        username = "lamymay";
//        password = "lamymay12345677Z!";
//        driverClassName = "com.mysql.cj.jdbc.Driver";
//
//        //表的一些配置 schemaName(数据库名) tableName(表名) tableAlias(表别名)
//        schemaName = "zero";
//        tableName = "t_we_chat_access_token";
//        tableAlias = "we_chat_access_token";
//    }


    @Override
    public String getDataSourceUrl() {
        return url;
    }

    @Override
    public String getDataSourceUsername() {
        return username;
    }

    @Override
    public String getDataSourcePassword() {
        return password;
    }

    @Override
    public String getDataSourceDriverClassName() {
        return driverClassName;
    }

    @Override
    public String getProjectRootNamespace() {
        return rootNamespace;
    }

    @Override
    public String getProjectMapperNamespace() {
        return mapperNamespace;
    }

    @Override
    public String getProjectModelNamespace() {
        return modelNamespace;
    }

    @Override
    public String getProjectServiceNamespace() {
        return serviceNamespace;
    }

    @Override
    public String getProjectServiceImplNamespace() {
        return serviceImplNamespace;
    }

    @Override
    public String getProjectControllerNamespace() {
        return controllerNamespace;
    }

    @Override
    public String getProjectOutputFolder() {
        return output;
    }

    @Override
    public boolean getOnlyModelMapperAndXml() {
        return onlyModelMapperAndXml;
    }

    @Override
    public boolean getOnlyEnableEndOfLineCommentAndDisableMultilineComment() {
        return commentFormatAsEndOfLine;
    }


    public String getAuthor() {
        if (this.author == null) {
            author = System.getProperty("user.name");
        }
        return this.author;
    }


    public static void main(String[] args) throws IOException {
        ArcCodeGeneratorContext arcCodeGeneratorContext = new ArcCodeGeneratorContext();
        System.out.println(arcCodeGeneratorContext.getAuthor());
        openOutputDir("D:\\新建文件夹");

//        System.out.println("File.separator=" + File.separator);
//        String downloads = "explorer %userProfile%" + File.separator + "Downloads";
//        Desktop.getDesktop().open(new File(downloads));

        // Process p = Runtime.getRuntime().exec(downloads);
//        exeCmd(downloads);
    }

    public static void exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
//            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line = null;
//            StringBuilder sb = new StringBuilder();
//            while ((line = br.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

// explorer %userProfile%\Downloads
//java中如何调用CMD命令 (2012-03-08 18:44:24)转载▼
//标签： 杂谈	分类： JAVA技术
//java的Runtime.getRuntime().exec(commandStr)可以调用执行cmd指令。
//
//cmd /c dir 是执行完dir命令后关闭命令窗口。
//
//cmd /k dir 是执行完dir命令后不关闭命令窗口。
//
//cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会关闭。
//
//cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。
//
//可以用cmd /?查看帮助信息。
//
//★CMD命令★
//1. gpedit.msc-----组策略
//2. sndrec32-------录音机
//3. Nslookup-------IP地址侦测器
//4. explorer-------打开资源管理器
//5. logoff---------注销命令
//6. tsshutdn-------60秒倒计时关机命令
//7. lusrmgr.msc----本机用户和组
//8. services.msc---本地服务设置
//9. oobe/msoobe /a----检查XP是否激活
//10. notepad--------打开记事本
//11. cleanmgr-------垃圾整理
//12. net start messenger----开始信使服务
//13. compmgmt.msc---计算机管理
//14. net stop messenger-----停止信使服务
//15. conf-----------启动netmeeting
//16. dvdplay--------DVD播放器
//17. charmap--------启动字符映射表
//18. diskmgmt.msc---磁盘管理实用程序
//19. calc-----------启动计算器
//20. dfrg.msc-------磁盘碎片整理程序
//21. chkdsk.exe-----Chkdsk磁盘检查
//22. devmgmt.msc--- 设备管理器
//23. regsvr32 /u *.dll----停止dll文件运行
//24. drwtsn32------ 系统医生
//25. rononce -p ----15秒关机
//26. dxdiag---------检查DirectX信息
//27. regedt32-------注册表编辑器
//28. Msconfig.exe---系统配置实用程序
//29. rsop.msc-------组策略结果集
//30. mem.exe--------显示内存使用情况
//31. regedit.exe----注册表
//32. winchat--------XP自带局域网聊天
//33. progman--------程序管理器
//34. winmsd---------系统信息
//35. perfmon.msc----计算机性能监测程序
//2. 36. winver---------检查Windows版本
//37. sfc /scannow-----扫描错误并复原
//38. taskmgr-----任务管理器（2000／xp／2003
//39. winver---------检查Windows版本
//40. wmimgmt.msc----打开windows管理体系结构(WMI)
//41. wupdmgr--------windows更新程序
//42. wscript--------windows脚本宿主设置
//43. write----------写字板
//44. winmsd---------系统信息
//45. wiaacmgr-------扫描仪和照相机向导
//46. winchat--------XP自带局域网聊天
//47. mem.exe--------显示内存使用情况
//48. Msconfig.exe---系统配置实用程序
//49. mplayer2-------简易widnows media player
//50. mspaint--------画图板
//51. mstsc----------远程桌面连接
//52. mplayer2-------媒体播放机
//53. magnify--------放大镜实用程序
//54. mmc------------打开控制台
//55. mobsync--------同步命令
//56. dxdiag---------检查DirectX信息
//57. drwtsn32------ 系统医生
//58. devmgmt.msc--- 设备管理器
//59. dfrg.msc-------磁盘碎片整理程序
//60. diskmgmt.msc---磁盘管理实用程序
//61. dcomcnfg-------打开系统组件服务
//62. ddeshare-------打开DDE共享设置
//63. dvdplay--------DVD播放器
//64. net stop messenger-----停止信使服务
//65. net start messenger----开始信使服务
//66. notepad--------打开记事本
//67. nslookup-------网络管理的工具向导
//68. ntbackup-------系统备份和还原
//69. narrator-------屏幕“讲述人”
//70. ntmsmgr.msc----移动存储管理器
//71. ntmsoprq.msc---移动存储管理员操作请求
//72. netstat -an----(TC)命令检查接口
//73. syncapp--------创建一个公文包
//74. sysedit--------系统配置编辑器
//75. sigverif-------文件签名验证程序
//76. sndrec32-------录音机
//77. shrpubw--------创建共享文件夹
//78. secpol.msc-----本地安全策略
//79. syskey---------系统加密，一旦加密就不能解开，保护windows xp系统的双重密码
//80. services.msc---本地服务设置
//81. Sndvol32-------音量控制程序
//82. sfc.exe--------系统文件检查器
//83. sfc /scannow---windows文件保护
//84. tsshutdn-------60秒倒计时关机命令
//3. 84. tsshutdn-------60秒倒计时关机命令
//85. tourstart------xp简介（安装完成后出现的漫游xp程序）
//86. taskmgr--------任务管理器
//87. eventvwr-------事件查看器
//88. eudcedit-------造字程序
//89. explorer-------打开资源管理器
//90. packager-------对象包装程序
//91. perfmon.msc----计算机性能监测程序
//92. progman--------程序管理器
//93. regedit.exe----注册表
//94. rsop.msc-------组策略结果集
//95. regedt32-------注册表编辑器
//96. rononce -p ----15秒关机
//97. regsvr32 /u *.dll----停止dll文件运行
//98. regsvr32 /u zipfldr.dll------取消ZIP支持
//99. cmd.exe--------CMD命令提示符
//100. chkdsk.exe-----Chkdsk磁盘检查
//101. certmgr.msc----证书管理实用程序
//102. calc-----------启动计算器
//103. charmap--------启动字符映射表
//104. cliconfg-------SQL SERVER 客户端网络实用程序
//105. Clipbrd--------剪贴板查看器
//106. conf-----------启动netmeeting
//107. compmgmt.msc---计算机管理
//108. cleanmgr-------垃圾整理
//109. ciadv.msc------索引服务程序
//110. osk------------打开屏幕键盘
//111. odbcad32-------ODBC数据源管理器
//112. oobe/msoobe /a----检查XP是否激活
//113. lusrmgr.msc----本机用户和组
//114. logoff---------注销命令
//115. iexpress-------木马捆绑工具，系统自带
//116. Nslookup-------IP地址侦测器
//117. fsmgmt.msc-----共享文件夹管理器
//118. utilman--------辅助工具管理器
//119. gpedit.msc-----组策略
//120. explorer-------打开资源管理器