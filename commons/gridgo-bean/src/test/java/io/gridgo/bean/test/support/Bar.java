package io.gridgo.bean.test.support;

import java.util.Map;

import com.dslplatform.json.CompiledJson;

import io.gridgo.bean.impl.BReferenceBeautifulPrint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@BReferenceBeautifulPrint
@CompiledJson
public class Bar {

    private boolean b;

    private Map<String, Integer> map;
}
