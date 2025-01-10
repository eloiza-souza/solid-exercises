package finalproject;

import finalproject.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void getCurrentDate_ShouldReturnFormattedDate() {
        // Arrange
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtil.DATE_FORMAT);
        LocalDateTime now = LocalDateTime.now();
        String expectedDate = dateFormatter.format(now);

        // Act
        String actualDate = DateUtil.getCurrentDate();

        // Assert
        assertNotNull(actualDate, "A data retornada não deve ser nula.");
        assertEquals(expectedDate.substring(0, 16), actualDate.substring(0, 16),
                "A data retornada deve estar no formato correto e corresponder ao tempo atual (até minutos).");
    }

    @Test
    void getCurrentDate_ShouldMatchDateFormat() {
        // Arrange
        String regex = "\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}";

        // Act
        String actualDate = DateUtil.getCurrentDate();

        // Assert
        assertTrue(actualDate.matches(regex), "A data retornada deve corresponder ao formato esperado: " + DateUtil.DATE_FORMAT);
    }
    @Test
    public void testGetCurrentDate_FormatIsCorrect() {
        // Cenário positivo: Verifica se a data retornada está no formato correto
        String currentDate = DateUtil.getCurrentDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.DATE_FORMAT);

        // Tenta fazer o parse da data retornada
        assertDoesNotThrow(() -> LocalDateTime.parse(currentDate, formatter));
    }

    @Test
    public void testGetCurrentDate_NotNull() {
        // Cenário positivo: Verifica se a data retornada não é nula
        String currentDate = DateUtil.getCurrentDate();
        assertNotNull(currentDate, "A data retornada não deve ser nula.");
    }

    @Test
    public void testGetCurrentDate_ChangesOverTime() throws InterruptedException {
        // Cenário positivo: Verifica se a data muda ao longo do tempo
        String date1 = DateUtil.getCurrentDate();
        Thread.sleep(1000); // Aguarda 1 segundo
        String date2 = DateUtil.getCurrentDate();

        assertNotEquals(date1, date2, "As datas devem ser diferentes após um intervalo de tempo.");
    }
}
