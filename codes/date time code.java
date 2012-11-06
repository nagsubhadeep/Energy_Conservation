
//CODE FOR DATE TIME AND DAY

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class testmodules {


  public static String now(String dateFormat) {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    return sdf.format(cal.getTime());

  }

  public static void  main(String arg[]) {
     System.out.println(testmodules.now("dd MMMMM yyyy"));
     System.out.println(testmodules.now("yyyyMMdd"));
     System.out.println(testmodules.now("dd.MM.yy"));
     System.out.println(testmodules.now("MM/dd/yy"));
     System.out.println(testmodules.now("yyyy.MM.dd G 'at' hh:mm:ss z"));
     System.out.println(testmodules.now("EEE, MMM d, ''yy"));
     System.out.println(testmodules.now("h:mm a"));
     System.out.println(testmodules.now("H:mm:ss:SSS"));
     System.out.println(testmodules.now("K:mm a,z"));
     System.out.println(testmodules.now("yyyy.MMMMM.dd GGG hh:mm aaa"));
  }
}

20 April 2010
20100420
20.04.10
04/20/10
2010.04.20 AD at 12:18:54 IST
Tue, Apr 20, '10
12:18 AM
0:18:54:437
0:18 AM,IST
2010.April.20 AD 12:18 AM


DATE_ADD('DATE_SUB(CURRENT_DATE(), INTERVAL 120 -" + j +" DAY)',INTERVAL "+ j + " DAY),CURRENT_TIME()



