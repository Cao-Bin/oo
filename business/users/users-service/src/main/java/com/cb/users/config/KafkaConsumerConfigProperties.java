package com.cb.users.config;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class KafkaConsumerConfigProperties {

    private String groupId;
    private boolean autoCommitOffset;
    private Long pollTimeout;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isAutoCommitOffset() {
        return autoCommitOffset;
    }

    public void setAutoCommitOffset(boolean autoCommitOffset) {
        this.autoCommitOffset = autoCommitOffset;
    }

    public Long getPollTimeout() {
        return pollTimeout;
    }

    public void setPollTimeout(Long pollTimeout) {
        this.pollTimeout = pollTimeout;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
