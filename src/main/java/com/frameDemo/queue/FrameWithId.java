package com.frameDemo.queue;

import com.frameDemo.frame.Frame;

public class FrameWithId {

    private long frameId;
    private Frame frame;

    public FrameWithId(long id, Frame frame){

        frameId = id;
        this.frame = frame;
    }


    public long getFrameId() {
        return frameId;
    }

    public Frame getFrame() {
        return frame;
    }
}
