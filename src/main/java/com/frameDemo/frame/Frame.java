package com.frameDemo.frame;

public class Frame {

    private FrameHead head;
    private FrameData data;


    private Frame(FrameHead head, FrameData data){

        this.head = head;
        this.data = data;
    }


    public static Frame initFrame(FrameHead head, FrameData data){

        Frame frame = new Frame(head, data);

        return frame;
    }


    public FrameHead getHead() {
        return head;
    }

    public void setHead(FrameHead head) {
        this.head = head;
    }

    public FrameData getData() {
        return data;
    }

    public void setData(FrameData data) {
        this.data = data;
    }


    public byte[] getFrameBytes(){

        byte[] bytes = new byte[12 + data.getDataLen()];

        byte[] h =  new byte[]{

                FrameHead.startFlagA,FrameHead.startFlagB,
                head.getVersion(),
                head.getId(),
                head.getRes()[0],
                head.getRes()[1],
                head.getRes()[2],
                head.getRes()[3],
                head.getLen()[0],
                head.getLen()[1],
                head.gethChk(),
                head.getdChk(),

        };
        for (int i = 0; i < 12; i++){
            bytes[i] = h[i];
        }

        for (int i = 12, j = 0; i < bytes.length && j < data.getDataLen(); i++,j++){
            bytes[i] = data.getData()[j];
        }


        return bytes;
    }
}
