package sys;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private UriParser parser;
    /**
     * Request의 parameters가 UriParser에서 parameters에 담은 데이터 분석 정보들을
     * getParmeters로 이어받아서 캐싱
     */
    private Map<String, Object> parameters;

    public Request(String url) {
        this.parser = new UriParser(url); // uri 분석
        this.parameters = this.parser.getParameters(); // 분석된 파라미터 저장
    }

    public boolean isValid() {
        return this.parser.isValidUri();
    }

    public <T> T getValue(String key, Class<T> cls) {

        Object value = parameters.get(key);

        if (cls == Integer.class) {
            return cls.cast(Integer.parseInt(value.toString()));
        } else if (cls == Long.class) {
            return cls.cast(Long.parseLong(value.toString()));
        } else if (cls == boolean.class) {
            return cls.cast(Boolean.parseBoolean(value.toString()));
        }

        return cls.cast(value);
    }

    public String getTarget() {
        return this.parser.getTarget();
    }
}
