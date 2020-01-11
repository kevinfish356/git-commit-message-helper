package com.kevinfish.ui;

import com.kevinfish.model.CommitTemplate;
import com.kevinfish.model.TypeAlias;
import com.kevinfish.storage.GitCommitMessageHelperSettings;
import com.kevinfish.utils.VelocityUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author fulin
 */
public class CommitMessage {
    private static final int MAX_LINE_LENGTH = 72; // https://stackoverflow.com/a/2120040/5138796
    private final String content;

    public CommitMessage(GitCommitMessageHelperSettings settings, TypeAlias typeAlias, String subject, String shortDescription, String closedIssues) {
        this.content = buildContent(
                settings,
                typeAlias,
                subject,
                shortDescription,
                closedIssues
        );
    }

    private String buildContent(GitCommitMessageHelperSettings settings,
                                TypeAlias typeAlias,
                                String subject,
                                String shortDescription,
                                String closedIssues
    ) {

        CommitTemplate commitTemplate = new CommitTemplate();
        if (typeAlias != null) {
            if (StringUtils.isNotBlank(typeAlias.getTitle())) {
                commitTemplate.setType(typeAlias.getTitle());
            }
        }
        if (StringUtils.isNotBlank(subject)) {
            commitTemplate.setSubject(subject);
        }
        if (StringUtils.isNotBlank(shortDescription)) {
            commitTemplate.setBody(shortDescription);
        }
        if (StringUtils.isNotBlank(closedIssues)) {
            commitTemplate.setCloses(closedIssues);
        }
        String template = settings.getDateSettings().getTemplate();
        return VelocityUtils.convert(template, commitTemplate);
    }

    @Override
    public String toString() {
        return content;
    }
}