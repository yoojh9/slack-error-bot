package com.slack.logback.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Slack {
	@Value("#{config['log.slack.enabled']}")
	private boolean enabled;
	@Value("#{config['log.slack.webHookUrl']}")
	private String webHookUrl;
	@Value("#{config['log.slack.channel']}")
	private String channel;

	public boolean isEnabled() {
		return enabled;
	}

	public String getWebHookUrl() {
		return webHookUrl;
	}

	public String getChannel() {
		return channel;
	}
}
