package multi_threading.guarded_suspension;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.List;

public class GuardedSuspensionMod {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        GuardObject guardObject = new GuardObject();
        new Thread(() -> {
            System.out.println("waiting for result...");
//            List<String> o = (List<String>) guardObject.get();
//            List<String> o = (List<String>) guardObject.get(100);
            List<String> o = (List<String>) guardObject.get(3000);
            System.out.println(STR."result：\{JSONObject.toJSONString(o)}");
        }, "t1").start();

        new Thread(() -> {
            System.out.println("process downloading...");
            guardObject.complete(new Download().downloadMock());
        }, "t2").start();
    }

}

class GuardObject {
    private Object response;

    public Object get() {
        synchronized (this) {
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return response;
        }
    }

    /**
     * Waiting with timeout period,
     * The principle of the join method in Thread
     *
     * @param timeout
     * @return
     */
    public Object get(long timeout) {
        synchronized (this) {
            long begin = System.currentTimeMillis();
            // elapsed time
            long passTime = 0;
            while (response == null) {
                try {
                    // the time to wait for this cycle
                    long waitTime = timeout - passTime;
                    if (waitTime <= 0) {
                        break;
                    }
                    this.wait(waitTime);
                    passTime = System.currentTimeMillis() - begin;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }
}

class Download {
    public List<String> downloadMock() {
        try {
            //mock download progress
            Thread.sleep(2500);
            return buildMockResult();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> buildMockResult() {
        String s = "success";
        List<String> res = Lists.newArrayList();
        res.add(s);
        return res;
    }
}
