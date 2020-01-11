package com.kevinfish.ui;

import com.kevinfish.model.TypeAlias;
import com.kevinfish.storage.GitCommitMessageHelperSettings;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.util.List;


public class CommitPanel {
    private JPanel mainPanel;
    private JComboBox changeType;
    private JTextField subject;
    private JTextField shortDescription;
    private JTextField closedIssues;

    public CommitPanel(Project project, GitCommitMessageHelperSettings settings) {
        //parameter
        List<TypeAlias> typeAliases = settings.getDateSettings().getTypeAliases();
        for (TypeAlias type : typeAliases) {
            changeType.addItem(type);
        }
    }

    JPanel getMainPanel() {
        return mainPanel;
    }

    CommitMessage getCommitMessage(GitCommitMessageHelperSettings settings) {
        return new CommitMessage(
                settings,
                (TypeAlias) changeType.getSelectedItem(),
                (String) subject.getText().trim(),
                shortDescription.getText().trim(),
                closedIssues.getText().trim()
        );
    }

}
