package chapter3;

/**
 * 隐式地使this应用逸出，不要这么做
 */
public class ThisEscape {

    public ThisEscape(EventSource source) {
        /*source是一个已经启动的线程，线程访问还没有构造好的对象，可能会造成意料不到的问题*/
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                /*this.*/doSomething(event);//ThisEscape对象还没有实例化完成，造成了隐性的this逸出
            }
        });
    }

    void doSomething(Event event) {

    }

    interface EventSource {
        void registerListener(EventListener listener);
    }

    interface EventListener {
        void onEvent(Event event);
    }

    interface Event {

    }
}
