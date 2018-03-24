package com.slack.logback.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.slack.logback.entity.Slack;

import ch.qos.logback.classic.Level;

@Component
public class LogConfig {

	@Value("#{config['log.level']}")
	private Level level;

	@Autowired
	private Slack slack;


	public Level getLevel() {
		return level;
	}

	public Slack getSlack() {
		return slack;
	}

}
