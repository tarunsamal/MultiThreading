package com.tarun.multi;

public class valley {

    public static void main(String[] args) {
        //System.out.println(countingValleys("DDUUDDUDUUUD"));
        int ar[] = {0 ,0 ,1, 0 ,0, 1 ,0};
        System.out.println(jumpingOnClouds(ar));
    }

    static int jumpingOnClouds(int[] c) {

return 0;
    }

    public static int countingValleys(String path) {
        int level =0;
        int valley =0;
        boolean isDown=false;

        for(int i=0 ; i < path.length() ; i++)
        {

            if(path.charAt(i)=='D')
            {
                if(level==0)
                {
                    isDown=true;
                }
                level--;
            }
            if(path.charAt(i)=='U')
            {
               level++;
            }
            if(level == 0 && isDown) {
                valley++;
                isDown=false;
            }
        }
        return valley;
    }

}
