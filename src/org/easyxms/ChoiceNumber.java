package org.easyxms;


class ChoiceNumber {

    private String cmd = null;
    private GetInput getInput = null;
    private OperateDataBase operateDataBase = null;
    private ServerInfoDAO serverInfoDAO = null;
    private ActionForChoiceNumber action = null;

    ChoiceNumber() {
        getInput = new GetInput();
        serverInfoDAO = new ServerInfoDAO();
        operateDataBase = new OperateDataBase();
        operateDataBase.connectDatabase();
        action = new ActionForChoiceNumber();
    }

    /** 循环得到输入的命令 */
    public void loopGetValue(){
        HelpPrompt.printWelcomeInfo();
        while (setInputValue()){
            if ("?".equals(cmd) || "？".equals(cmd)){
                HelpPrompt.printPrompt();
            } else if ("1".equals(cmd)){
                action.addServerFromCommandLine(serverInfoDAO);
            } else if ("2".equals(cmd)){
                action.addServerFromExcelFile(serverInfoDAO);
            } else if ("3".equals(cmd)){
                action.listIPGroupFromDatabase(serverInfoDAO);
            } else if ("4".equals(cmd)){
                action.listGroupFromDatabase(serverInfoDAO);
            } else if ("5".equals(cmd)){
                action.deleteServerInfoFromDatabase(operateDataBase);
            } else if ("6".equals(cmd)){
                action.connectServerForSSHSFTP(operateDataBase,"ssh");
            } else if ("7".equals(cmd)){
                System.out.println("上传文件");
            } else if ("8".equals(cmd)){
                System.out.println("清屏");
            }
        }
    }


    /** 显示提示符等到用户输入*/
    public boolean setInputValue(){
        HelpPrompt.printProgramName();
        cmd = getInput.getInputFromStandardInput();
        if ("q".equals(cmd) || "Q".equals(cmd)){
            Quit.quit();
        }
        return true;
    }
}
