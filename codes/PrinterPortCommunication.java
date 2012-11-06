public class PrinterPortCommunication {

    static PrinterPortCommunication instance = null;
    public final String[] PORT_TYPE = {"Serial Port", "Parallel Port"};
    private String parallelPortName = null;
    private OutputStream outputStream = null;
    private ParallelPort parallelPort = null;
    private CommPortIdentifier port = null;
    static int lineCountLimit = 0;
    static byte[] headerByte = null;
    static int lineCounter = 0;
    private static Logger logger = Logger.getLogger(PrinterPortCommunication.class);

    public static PrinterPortCommunication getInstance() {
        if (instance == null) {

            instance = new PrinterPortCommunication();

        }
        return instance;


    }

    public void init(String parallelPortName, String headerText, int lineCountLimit) throws OnlinePrintException {
        try {
            this.parallelPortName = parallelPortName;
            PrinterPortCommunication.lineCountLimit = lineCountLimit;
            headerText = headerText + " \n";
            headerByte = headerText.getBytes("UTF8");
 
            port = CommPortIdentifier.getPortIdentifier(parallelPortName);
            parallelPort = (ParallelPort) port.open("EventRecorder", 50);
            outputStream = parallelPort.getOutputStream();
            logger.info("\nport.portType = " + port.getPortType());
            logger.info("port type = " + PORT_TYPE[port.getPortType() - 1]);
            logger.info("port.name = " + port.getName());
        } catch (IOException ex) {

            throw new OnlinePrintException("No paper to print->" + "outputStream" + "\n");
        } catch (Exception ex) {

            throw new OnlinePrintException("can not fine this port->" + parallelPortName + "\n");

        }
    }

    public void print(String printerCodes) throws OnlinePrintException {
        try {
            printerCodes = printerCodes + " \n";
            byte[] byteArray = printerCodes.getBytes("UTF8");


            if (lineCounter == lineCountLimit) {

                outputStream.write(headerByte);
                outputStream.flush();
                outputStream.close();
                lineCounter = 0;
            }

            outputStream.write(byteArray);
            outputStream.flush();
            outputStream.close();
            lineCounter++;
      
        } catch (Exception ex) {
            throw new OnlinePrintException("Error in port's outputStream.first initiate the         PrintPortCommunication class.\n");
        }

    }
}


code to send print:
 PrinterPortCommunication.getInstance().init("LPT1", "", 5);
   public synchronized void update(Observer observer, final Object obj) {

        try {
        
                        PrinterPortCommunication.getInstance().print("event description....");
               }
            catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            logger.warn("***Exception***" + ex.toString());
        }

 
    }