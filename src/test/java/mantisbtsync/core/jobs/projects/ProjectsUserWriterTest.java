/**
 *
 */
package mantisbtsync.core.jobs.projects;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mantisbtsync.core.junit.AbstractSqlWriterTest;

import org.junit.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import biz.futureware.mantis.rpc.soap.client.AccountData;

import com.ninja_squad.dbsetup.operation.Operation;

/**
 * @author jdevarulrajah
 *
 */
public class ProjectsUserWriterTest extends AbstractSqlWriterTest {

	@Autowired
	CompositeItemWriter<AccountData> projectUsersWriter;

	public StepExecution getStepExecution() {

		final Map<String, JobParameter> map = new HashMap<String, JobParameter>();
		final JobParameters jobParams = new JobParameters(map);
		final JobExecution exec = MetaDataInstanceFactory.createJobExecution(
				"testJob", 1L, 1L, jobParams);

		exec.getExecutionContext().put("mantis.loop.project_id", BigInteger.ONE);

		final StepExecution stepExecution = exec.createStepExecution("testStep");
		return stepExecution;
	}

	@Test
	public void test() throws Exception {

		lauchOperation(deleteAllFrom("mantis_project_user_list_table",
				"mantis_user_table",
				"mantis_custom_field_project_table",
				"mantis_custom_field_table",
				"mantis_enum_custom_field_types",
				"mantis_project_hierarchy_table",
				"mantis_category_table",
				"mantis_project_version_table",
				"mantis_project_table"));

		final Operation op = sequenceOf(
				insertInto("mantis_project_table")
				.columns("id", "name")
				.values(1, "project_1")
				.build(),

				insertInto("mantis_user_table")
				.columns("id", "name")
				.values(1, "old_user_1")
				.build(),

				insertInto("mantis_project_user_list_table")
				.columns("user_id", "project_id")
				.values(1, 1)
				.build());

		lauchOperation(op);

		projectUsersWriter.write(buildItems());

		final List<AccountData> results = getJdbcTemplate().query("SELECT usr.id, usr.name"
				+ " FROM mantis_user_table usr"
				+ " INNER JOIN mantis_project_user_list_table pul ON pul.user_id = usr.id"
				+ " WHERE pul.project_id = 1",
				new BeanPropertyRowMapper<AccountData>(AccountData.class));

		assertEquals(2, results.size());

		for (final AccountData item : results) {
			if (item.getId() == BigInteger.ONE) {
				assertEquals("new_user_1", item.getName());
			} else {
				assertEquals(BigInteger.valueOf(2), item.getId());
				assertEquals("new_user_2", item.getName());
			}
		}
	}

	/**
	 * Build the items to write.
	 *
	 * @return items to write
	 */
	private List<AccountData> buildItems() {
		final List<AccountData> items = new ArrayList<AccountData>();

		final AccountData item1 = new AccountData();
		item1.setId(BigInteger.valueOf(1));
		item1.setName("new_user_1");

		final AccountData item2 = new AccountData();
		item2.setId(BigInteger.valueOf(2));
		item2.setName("new_user_2");

		items.add(item1);
		items.add(item2);

		return items;
	}

	/**
	 * @return the projectUsersWriter
	 */
	public final CompositeItemWriter<AccountData> getProjectUsersWriter() {
		return projectUsersWriter;
	}

	/**
	 * @param projectUsersWriter the projectUsersWriter to set
	 */
	public final void setProjectUsersWriter(
			final CompositeItemWriter<AccountData> projectUsersWriter) {
		this.projectUsersWriter = projectUsersWriter;
	}

}