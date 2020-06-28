package cpm.each.springcloud;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    public static void main(String[] args) {
        try {
            ZooKeeper zk = new ZooKeeper("192.168.1.199:2181", 20000, null);
            System.out.println(zk.getClass());
            if(zk.exists("/test", false) == null){
                zk.create("/test", "znode1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            System.out.println(new String(zk.getData("/test", false, null)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
