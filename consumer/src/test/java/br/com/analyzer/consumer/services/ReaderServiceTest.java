package br.com.analyzer.consumer.services;

import br.com.analyzer.consumer.components.BuilderRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReaderServiceTest {

    @InjectMocks
    ReaderService reader;

    @Mock
    BuilderRegistry builder;

    @Mock
    AnalyzerService analyzer;

    @Mock
    FileService files;

    @Mock
    ReportService report;

    @Mock
    LogService log;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(builder, "salesmanId", "001");
        ReflectionTestUtils.setField(builder, "customerId", "002");
        ReflectionTestUtils.setField(builder, "saleId", "003");
        ReflectionTestUtils.setField(builder, "delimiterChar", "ç");
        ReflectionTestUtils.setField(builder, "itemDelimiterChar", ",");
        ReflectionTestUtils.setField(builder, "valuesDelimiterChar", "-");
    }

    @Test
    public void mustReadNewFileFromQueue() throws Exception {
        String message = "001ç1234567891234çPedroç50000\r" +
        "001ç3245678865434çPauloç40000.99\r" +
        "002ç2345675434544345çJose da SilvaçRural\r" +
        "002ç2345675433444345çEduardo PereiraçRural\r" +
        "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\r" +
        "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]Paulo";

        reader.read(message.getBytes(), "lote01.dat");

        verify(files, times(1)).makeSureAllDirectoriesExist();

        verify(builder, times(6)).build(any());

        verify(analyzer, times(1)).analize(any());

        verify(report, times(1)).generate(any(), any());

        verify(log, times(0)).generate(any(), any());
    }

}