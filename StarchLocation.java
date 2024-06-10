/***********************************
 * Course: Lehigh CSE017-SU2024
 * Assignment: Midterm (v01)
 * Name: Yinglong Lin
 * UID: yile22
 * ********************************* */

public enum StarchLocation{
    BOTTOM( "bottom", 1 ),
    BOTTOM_AND_TOP( "bottom & top", 2),
    BOTTOM_AND_2SIDES( "bottom & 2 sides", 3 );

    /** Description of where the starch appears */
    private final String descr;
    /** The number of times the starch appears */
    private final int starch_count;

    StarchLocation( String descr, int count ){
        this.descr = descr;
        this.starch_count = count;
    }

    public String getDescription(){ return descr; }
    public int getStarchCount(){ return starch_count; }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append( "StarchLocation(cnt=" ).append( starch_count );
        sb.append( ", \"").append( descr ).append( "\")" );
        return sb.toString();
    }

    public static void main(String[] args){
        StarchLocation sExample = StarchLocation.BOTTOM;
        System.out.println( sExample );
        System.out.println( sExample.getStarchCount() );
    }
}