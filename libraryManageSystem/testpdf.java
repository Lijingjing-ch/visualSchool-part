package libraryManageSystem;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class testpdf {
    public static void main(String[] args) {
        String filePath = "D:/暑期学校-2/专业实训/【驰强】漠河的香槟玫瑰.txt";
        try {
            String fileContent = readFile(filePath, "UTF-8");
            System.out.println(fileContent); // 输出文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String filePath, String encoding) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encoding))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }
}

