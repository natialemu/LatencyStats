package domain.builder;

import domain.execution.ExecutionGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecutionGraphBuilder {

    //private static final String METHOD_CALL_LOG_FILE = "/Users/nalemu/Documents/EWE/dayaway/Logs/method_call_log.csv";

    public static ExecutionGraph getExecutionGraph(String appName, String requestID){
        ExecutionGraph graph = new ExecutionGraph();
        /**
         * Use DAOs to get the info neccessary to build the graph
         *
         */
//        try {
//            File file =
//                    new File(METHOD_CALL_LOG_FILE);
//            Scanner sc = new Scanner(file);
//            while (sc.hasNextLine()){
//                String[] currentLine = sc.nextLine().split(",");
//                assert(currentLine.length == 2);
//                graph.addMethodCall(currentLine[0],currentLine[1]);
//
//            }
//        }catch (FileNotFoundException fnfe){
//            fnfe.printStackTrace();
//        }
        return graph;
    }
}
