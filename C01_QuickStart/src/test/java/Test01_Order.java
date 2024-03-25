import io.github.coffee330501.droolsLearn.entity.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Test01_Order {
 
    @Test
    public void test01() {
        //第一步 获取KieServices
        KieServices kieServices = KieServices.Factory.get();
        //第二步获取kieContainer
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //第三步获取kieSession
        KieSession simpleRuleKSession = kieClasspathContainer.newKieSession();
        //新建事实对象
        Order order = new Order();
        order.setAmount(235);
        //第四步 插入事实对象到session中
        simpleRuleKSession.insert(order);
        //第五步 执行规则引擎
        simpleRuleKSession.fireAllRules();
        //第六步 关闭规则引擎
        simpleRuleKSession.dispose();
        System.out.println("规则执行完成，关闭规则");
    }
}