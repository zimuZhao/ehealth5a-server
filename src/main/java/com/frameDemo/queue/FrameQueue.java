package com.frameDemo.queue;

import com.frameDemo.frame.Frame;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FrameQueue {

    private  static volatile FrameQueue thisFrameQueue;
    private Lock lock;

    private Queue<FrameWithId> queue;

    private FrameQueue(){

        queue = new LinkedList<FrameWithId>();
        lock = new ReentrantLock();
    }

    public static FrameQueue getFrameQueue(){

        if (thisFrameQueue == null)
            thisFrameQueue = new FrameQueue();

        return thisFrameQueue;
    }

    public  void addFrame( long id, Frame frame){

        try {
            lock.lock();
            queue.add(new FrameWithId(id, frame));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public  FrameWithId getFrame(){

        try {
            lock.lock();
            //The head of this queue, or <tt>null</tt> if this queue is empty
            return queue.poll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }

}
