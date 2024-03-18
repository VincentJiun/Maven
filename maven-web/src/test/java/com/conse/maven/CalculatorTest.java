package com.maventest;

import org.junit.Test;
import com.maventest.Calculator;

// 靜態導入的效果是將 Accert類中的靜態資源導入當前類
// 這樣一來，在當前類中就可以直接使用Assert類中的靜態資源，不須要寫類名
import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void testSum(){
        // 1. 創建calculator對象
        Calculator calculator = new Calculator();

        // 2. 調用Calcultor對象的方式，獲取道程序運行實際的結果
        int  actualResult = calculator.sum(5, 2);

        // 3. 聲明一個變量，表示程序運行期待的結果
        int expectedResult = 7;

        // 4. 使用斷言來判斷實際結果和期待結果是否一致
        // 如果一致:測試通過， 不會拋出異常
        // 如果不一致: 拋出異常，測試失敗
        assertEquals(expectedResult, actualResult);
    }
}
