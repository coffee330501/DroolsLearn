import io.github.coffee330501.entity.Student;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * 查询
 * 不需要 fireAll
 */
public class Test05_RHS_Advance {
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
    public void test1() {
        simpleRuleKSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rhs_advance_halt"));
    }

    @Test
    public void test2() {
        simpleRuleKSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rhs_advance_getWorkingMemory"));
    }

    @Test
    public void test3() {
        simpleRuleKSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rhs_advance_getRule"));
    }

    @After
    public void after() {
        //第六步 关闭规则引擎
        simpleRuleKSession.dispose();
        System.out.println("规则执行完成，关闭规则");
    }
}
