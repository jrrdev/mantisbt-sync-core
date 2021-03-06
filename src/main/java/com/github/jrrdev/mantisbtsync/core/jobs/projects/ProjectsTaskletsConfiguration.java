/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 J�rard Devarulrajah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.jrrdev.mantisbtsync.core.jobs.projects;

import java.math.BigInteger;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import biz.futureware.mantis.rpc.soap.client.MantisConnectBindingStub;

import com.github.jrrdev.mantisbtsync.core.common.auth.PortalAuthManager;
import com.github.jrrdev.mantisbtsync.core.jobs.projects.tasklets.MantisLoginTasklet;
import com.github.jrrdev.mantisbtsync.core.jobs.projects.tasklets.ProjectsExtractorTasklet;
import com.github.jrrdev.mantisbtsync.core.jobs.projects.tasklets.ProjectsListTasklet;

/**
 * Configuration for the taskets  used to sync MantisBT projects.
 *
 * @author jrrdev
 *
 */
@Configuration
public class ProjectsTaskletsConfiguration {

	// tag::tasklet[]

	/**
	 * Build the tasklet calling mc_login to get the user_acces_level.
	 *
	 * @param authManager
	 * 			The portal auth manager
	 * @param clientStub
	 * 			Axis client stub
	 * @param userName
	 * 			MantisBT username. If anonymous access is used, should be an empty string.
	 * @param password
	 * 			MantisBT password. If anonymous access is used, should be an empty string.
	 * @return the tasklet
	 */
	@Bean
	@StepScope
	public MantisLoginTasklet mantisLoginTasklet(final PortalAuthManager authManager,
			final MantisConnectBindingStub clientStub,
			@Value("#{jobParameters['mantis.username']}") final String userName,
			@Value("#{jobParameters['mantis.password']}") final String password) {

		final MantisLoginTasklet tasklet = new MantisLoginTasklet();
		tasklet.setAuthManager(authManager);
		tasklet.setClientStub(clientStub);
		tasklet.setUserName(userName);
		tasklet.setPassword(password);

		return tasklet;
	}

	/**
	 * Build the tasklet which retrieves all subprojects related to the main
	 * project passed as job parameter.
	 *
	 * @param authManager
	 * 			The portal auth manager
	 * @param clientStub
	 * 			Axis client stub
	 * @param jdbcTemplate
	 * 			JDBC template
	 * @param userName
	 * 			MantisBT username. If anonymous access is used, should be an empty string.
	 * @param password
	 * 			MantisBT password. If anonymous access is used, should be an empty string.
	 * @param projectId
	 * 			The id of the project
	 * @return the tasklet
	 */
	@Bean
	@StepScope
	public ProjectsListTasklet mantisProjectsListTasklet(final PortalAuthManager authManager,
			final MantisConnectBindingStub clientStub, final JdbcTemplate jdbcTemplate,
			@Value("#{jobParameters['mantis.username']}") final String userName,
			@Value("#{jobParameters['mantis.password']}") final String password,
			@Value("#{jobParameters['mantis.project_id']}") final Long projectId) {

		final ProjectsListTasklet tasklet = new ProjectsListTasklet();
		tasklet.setAuthManager(authManager);
		tasklet.setClientStub(clientStub);
		tasklet.setUserName(userName);
		tasklet.setPassword(password);
		tasklet.setJdbcTemplate(jdbcTemplate);
		tasklet.setProjectId(BigInteger.valueOf(projectId));

		return tasklet;
	}

	/**
	 * Build the tasklet that gets the list of projects to sync from the job execution
	 * context.
	 *
	 * @return the tasklet
	 */
	@Bean
	@StepScope
	public ProjectsExtractorTasklet mantisProjectExtractorTasklet() {

		return new ProjectsExtractorTasklet();
	}

	// end::tasklet[]

	// tag::listener[]

	/**
	 * Build the execution promotion listener used to store the access level (mantis.acess_level)
	 * in the job execution context.
	 *
	 * @return the execution promotion listener
	 */
	@Bean
	@StepScope
	public ExecutionContextPromotionListener mantisLoginPromotionListener() {
		final ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
		listener.setKeys(new String[] {"mantis.acess_level"});

		return listener;
	}

	/**
	 * Build the execution promotion listener used to store the list of projects to sync
	 * (mantis.loop.projects_to_process) in the job execution context.
	 *
	 * @return the execution promotion listener
	 */
	@Bean
	@StepScope
	public ExecutionContextPromotionListener mantisProjectsListListener() {
		final ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
		listener.setKeys(new String[] {"mantis.loop.projects_to_process"});

		return listener;
	}

	/**
	 * Build the execution promotion listener used to store the current project to sync
	 * (mantis.loop.project_id) in the job execution context.
	 *
	 * @return the execution promotion listener
	 */
	@Bean
	@StepScope
	public ExecutionContextPromotionListener mantisProjectExtractorListener() {
		final ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
		listener.setKeys(new String[] {"mantis.loop.project_id"});

		return listener;
	}

	// end::listener[]
}
