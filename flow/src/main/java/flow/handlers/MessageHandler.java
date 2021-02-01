package flow.handlers;

import modules.Message;

public abstract class MessageHandler<T extends Message> {


    /**
     *
     *  handle with message
     *
     * @param message message to handle
     * @return true when success, false when fail
     */
    public abstract boolean handle(T message);

    public boolean doHandle(Message message){

        T msg = (T)message;
        return handle(msg);

    }



}
