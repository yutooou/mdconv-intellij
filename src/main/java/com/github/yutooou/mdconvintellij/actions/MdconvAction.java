package com.github.yutooou.mdconvintellij.actions;

import com.github.yutooou.mdconvintellij.icon.MdconvIcon;
import com.github.yutooou.mdconvintellij.services.ConvertService;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import jetbrains.buildServer.messages.serviceMessages.Message;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class MdconvAction extends ActionGroup {

    @Override
    public AnAction[] getChildren(AnActionEvent e) {
        return new AnAction[]{
                Separator.getInstance(),
                new AnAction("mdconv", "mdconv - 转为word格式", MdconvIcon.mdconv_icon) {
                    private ConvertService convertService = ApplicationManager.getApplication().getService(ConvertService.class);
                    @Override
                    public void actionPerformed(@NotNull AnActionEvent event) {
                        // 处理子菜单项 1 的逻辑
                        VirtualFile file = event.getData(CommonDataKeys.VIRTUAL_FILE);
                        if (file == null) {
                            Messages.showErrorDialog("非系统文件夹或文件", "mdconv转换错误");
                            return;
                        }
                        try {
                            convertService.convert(file);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                },
                Separator.getInstance(),
        };
    }
}

