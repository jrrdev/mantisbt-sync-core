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
package mantisbtsync.core.jobs;

import mantisbtsync.core.common.auth.PortalAuthManager;
import mantisbtsync.core.common.listener.CloseAuthManagerListener;
import mantisbtsync.core.jobs.issues.beans.BugBean;
import mantisbtsync.core.jobs.issues.readers.IssuesReader;
import mantisbtsync.core.jobs.issues.tasklets.IssuesLastRunExtractorTasklet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the job of Mantis issues syncing.
 * Parameters for this job are :
 * 	- mantis.username
 *  - mantis.password
 *  - mantis.project_id
 *
 * @author jdevarulrajah
 *
 */
@Configuration
public class JobIssuesConfiguration {

	@Bean
	public Job syncIssuesJob(final JobBuilderFactory jobs, final Step issuesLastSuccessExtractorStep,
			final Step issuesSyncStep, final Step authIssuesStep, final CloseAuthManagerListener closeIssuesListener) {

		return jobs.get("syncIssuesJob")
				.incrementer(new RunIdIncrementer())
				.listener(closeIssuesListener)
				.flow(authIssuesStep)
				.next(issuesLastSuccessExtractorStep)
				.next(issuesSyncStep)
				.end()
				.build();
	}

	@Bean
	public CloseAuthManagerListener closeIssuesListener(final PortalAuthManager authManager) {
		final CloseAuthManagerListener listener = new CloseAuthManagerListener();
		listener.setMgr(authManager);
		return listener;
	}

	@Bean
	public Step authIssuesStep(final StepBuilderFactory stepBuilderFactory,
			final MethodInvokingTaskletAdapter authTasklet) {

		return stepBuilderFactory.get("authIssuesStep").allowStartIfComplete(true)
				.tasklet(authTasklet).build();
	}

	@Bean
	public Step issuesLastSuccessExtractorStep(final StepBuilderFactory stepBuilderFactory,
			final IssuesLastRunExtractorTasklet mantisLastRunExtractorTasklet,
			final StepExecutionListener mantisLastRunExtractorPromotionListener) {

		return stepBuilderFactory.get("issuesLastSuccessExtractorStep")
				.tasklet(mantisLastRunExtractorTasklet)
				.listener(mantisLastRunExtractorPromotionListener)
				.build();
	}

	@Bean
	public Step issuesSyncStep(final StepBuilderFactory stepBuilderFactory,
			final IssuesReader issuesReader,
			final CompositeItemWriter<BugBean> compositeIssuesWriter) {

		return stepBuilderFactory.get("issuesSyncStep")
				.<BugBean, BugBean> chunk(20)
				.reader(issuesReader)
				.writer(compositeIssuesWriter)
				.build();
	}
}
