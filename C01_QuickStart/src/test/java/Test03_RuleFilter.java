import io.github.coffee330501.droolsLearn.entity.ComparisonOperatorEntity;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

public class Test03_RuleFilter {
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

    /**
     * 控制只执行某个规则
     */
    @Test
    public void testRule() {
        //新建事实对象
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        entity.setList(list);
        entity.setName("张三");

        //第四步 插入事实对象到session中
        simpleRuleKSession.insert(entity);

        //第五步 执行规则引擎
        simpleRuleKSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_contains"));
    }

    @After
    public void after() {
        //第六步 关闭规则引擎
        simpleRuleKSession.dispose();
        System.out.println("规则执行完成，关闭规则");
    }
}
