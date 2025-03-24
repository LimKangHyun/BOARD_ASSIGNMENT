package sys;

import java.util.HashMap;
import java.util.Map;

public class UriParser {

    private String URI;
    private String controllerCode;
    private String target;

    private boolean isValidUri = true;
    private Map<String, Object> parameters = new HashMap<>();

    public UriParser(String uri) {
        this.URI = parse(uri);
    }

    protected String parse(String uri) { // URI의 필요한 데이터 추출

        if(!uri.startsWith("/")) {
            isValidUri = false;
            return uri;
        }

        String[] uriSplit = uri.split("\\?", 2); // ?를 구분자로 사용
        if(uriSplit.length != 2) {
            setParameter(uriSplit[1]);
        }

        String[] uriFront = uriSplit[0].split("/");

        if (uriFront.length != 3) {
            isValidUri = false;
            return uri;
        }

        controllerCode = uriFront[1];
        target = uriFront[2];

        return uri;
    }

    protected void setParameter(String uriPart) {
        try {
            if (uriPart.contains("&")) { // 파라미터가 여러개일 때
                String[] split = uriPart.split("&");

                for (String s : split) {

                    String[] parameterData = s.split("=", 2);

                    if (parameterData[1].equals("")) {
                        throw new IllegalArgumentException("잘못된 파라미터 값이 입력되었습니다. URL을 확인해주세요.");
                    }
                }
            } else {
                String[] split = uriPart.split("=", 2);
                if (split.length != 2) {
                    throw new IllegalArgumentException("잘못된 파라미터 값이 입력되었습니다. URL을 확인해주세요.");
                }
                parameters.put(split[0], split[1]);
            }
        } catch ( ArrayIndexOutOfBoundsException | IllegalArgumentException e ) {
            this.isValidUri = false;
        }
    }

    public String getURI() {
        return URI;
    }

    public String getControllerCode() {
        return controllerCode;
    }

    public String getTarget() {
        return target;
    }

    public boolean isValidUri() {
        return isValidUri;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
