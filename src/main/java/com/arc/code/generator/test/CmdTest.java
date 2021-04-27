package com.arc.code.generator.test;

import com.arc.code.generator.config.properties.ArcPropertiesProvider;
import com.arc.code.generator.config.properties.impl.ArcCodeGeneratorContext;
import com.arc.code.generator.model.domain.meta.TableMeta;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static com.arc.code.generator.controller.data.GenerateCodeV1Controller.openOutputDir;

/**
 * @author may
 * @since 2021/4/27 14:24
 */
@Slf4j
public class CmdTest {


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

    private void generateStandardModel(Map<String, Object> parameterMap) throws Exception {


        //输出文件处理
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(".");
        //target  目录
        String path = new File(resource.getPath()).getParent();
        //todo 参数校验    target +  传入参数
        path = path + File.separator + ((ArcPropertiesProvider) parameterMap.get(ArcPropertiesProvider.class.getName())).getProjectProperties().getOutputFolder() + File.separator;

        TableMeta tableMeta = (TableMeta) parameterMap.get(TableMeta.class.getName());
        String className = tableMeta.getClassName();
        String newFilePath = path + className + ".java";
        parameterMap.put("output", newFilePath);
        log.debug("文件名称={}", newFilePath);
        File javaFile = new File(newFilePath);
        if (!javaFile.exists()) {
            //createNewFile这个方法只能在一层目录下创建文件，不能跳级创建，尽管可以用mkdir(s)创建多层不存在的目录，但是不要直接一个File对象搞定目录和文件都需要创建的情况，可以在已有目录下直接用createNewFile创建文件
            if (!javaFile.getParentFile().exists()) {
                boolean mkdirs = javaFile.getParentFile().mkdirs();
                log.debug("父级路径创建结果={}", mkdirs);
            }

            boolean result = javaFile.createNewFile();
            log.info("javaFile.createNewFile()={}", result);

        }
//        Template template = configuration.getTemplate("model.ftl");
//        template.process(parameterMap, new FileWriter(javaFile));

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