import io.github.coffee330501.entity.Student;
import io.github.coffee330501.service.UserService;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询
 * 不需要 fireAll
 */
public class Test02_Query {
    KieSession simpleRuleKSession;

    @Before
    public void before() {
        //第一步 获取KieServices
        KieServices kieServices = KieServices.Factory.get();
        //第二步获取kieContainer
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //第三步获取kieSession
        simpleRuleKSession = kieClasspathContainer.newKieSession();
    }

    @Test
    public void test() {
        Student student1 = new Student();
        student1.setAge(20);
        student1.setName("张三");

        Student student2 = new Student();
        student2.setAge(7);
        student2.setName("李四");

        Student student3 = new Student();
        student3.setAge(22);
        student3.setName("王五");

        simpleRuleKSession.insert(student1);
        simpleRuleKSession.insert(student2);
        simpleRuleKSession.insert(student3);

        // 查询
        QueryResults query_1 = simpleRuleKSession.getQueryResults("query_1");
        System.out.println("query_1 查询结果: \n数量:" + query_1.size());
        for (QueryResultsRow resultsRow : query_1) {
            System.out.println(resultsRow.get("$s"));
        }

        QueryResults query_2 = simpleRuleKSession.getQueryResults("query_2", "李四");
        System.out.println("\nquery_2 查询结果: \n数量:" + query_2.size());
        for (QueryResultsRow resultsRow : query_2) {
            System.out.println(resultsRow.get("$s"));
        }
    }

    @After
    public void after() {
        //第六步 关闭规则引擎
        simpleRuleKSession.dispose();
        System.out.println("规则执行完成，关闭规则");
    }
}
