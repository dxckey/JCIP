package chapter3;

public class ThisEscape {

    public ThisEscape(EventSource source) {
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
