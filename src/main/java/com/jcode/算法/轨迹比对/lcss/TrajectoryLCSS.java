package com.jcode.算法.轨迹比对.lcss;

public class TrajectoryLCSS {
    private Coordinate[] L1;
    private Coordinate[] L2;
    private Coordinate[] LCS;
    private double distThre;
    private double matchRatio;
    private static final double DEFAULT_DISTTHRE=0.0005;//经纬度差值阈值大约0.001在地图上相差80-90米
    private int commonLen;

    public TrajectoryLCSS(Coordinate[] L1,Coordinate[] L2) {
        this.L1=L1;
        this.L2=L2;
        this.distThre=DEFAULT_DISTTHRE;
    }
    /**
     * @param L1
     * @param L2
     * @param dist_thre
     */
    public TrajectoryLCSS(Coordinate[] L1,Coordinate[] L2,double dist_thre) {
        this(L1, L2);
        this.distThre=dist_thre;
    }
    /**
     * 动态规划计算所有子问题
     * @return
     */
    public int[][] getTypeMatrix(){
        int[][] type=new int[L1.length+1][L2.length+1];
        for(int i=L1.length-1;i>=0;i--) {
            for(int j=L2.length-1;j>=0;j--) {
                if(isClose(L1[i],L2[j])){
                    System.out.println(L1[i]);
                    System.out.println(L2[j]);
                    type[i][j]=type[i+1][j+1]+1;
                    commonLen++;
                }else {
                    type[i][j]=Math.max(type[i][j+1], type[i+1][j]);
                }
            }
        }
        return type;
    }
    /**
     * 查看两点是否可以判定为同点
     * @param p1
     * @param p2
     * @return
     */
    public boolean isClose(Coordinate p1,Coordinate p2) {
        double x_abs=Math.abs(p1.x-p2.x);
        double y_abs=Math.abs(p1.y-p2.y);
        if(x_abs<distThre&&y_abs<distThre)
            return true;
        return false;
    }
    /**
     * @return
     */
    public Coordinate[] genLCSS() {
        int[][] typematrix=getTypeMatrix();
        Coordinate[] res = new Coordinate[commonLen];
        int i=0,j=0;
        int index=0;
        while(i<L1.length&&j<L2.length) {
            if(isClose(L1[i],L2[j])) {
                System.out.println(index);
                System.out.println(i);
                System.out.println(commonLen);
                System.out.println(L1[i]);
                System.out.println(L2[j]);
                res[index++]=L1[i];
                i++;
                j++;
            }else if(typematrix[i+1][j]>=typematrix[i][j+1]) {
                i++;
            }else {
                j++;
            }
        }
        LCS=res;
        matchRatio=this.LCS.length/(double)(Math.min(L1.length,L2.length));
        return res;
    }
    /**
     * 更新Ratio
     * @return
     */
    public double getMatchRatio() {
        if(matchRatio==0) {
            genLCSS();
        }
        return this.LCS.length/(double)(Math.min(L1.length,L2.length));
    }

    public static void main(String[] args) {
        Coordinate[] coor1=new Coordinate[5];
        coor1[0]=new Coordinate(114.300, 30.1);
        coor1[1]=new Coordinate(115.302, 30.101);
        coor1[2]=new Coordinate(116.3023, 30.1002);
        coor1[3]=new Coordinate(114.30235, 30.1011);
        coor1[4]=new Coordinate(114.304, 30.1003);
        Coordinate[] coor2=new Coordinate[5];
        coor2[0]=new Coordinate(114.300, 30.1);
        coor2[1]=new Coordinate(114.302, 30.101);
        coor2[2]=new Coordinate(114.3023, 30.1002);
        coor2[3]=new Coordinate(114.30235, 30.1011);
        coor2[4]=new Coordinate(114.304, 30.1003);

        TrajectoryLCSS lcss=new TrajectoryLCSS(coor1, coor2, 0.001);
        Coordinate[] coors=lcss.genLCSS();
        System.out.println(lcss.getMatchRatio());
    }
}
