/*
 * Copyright (c) 2016 Alexander Grün
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.unknownreality.dataframe.csv;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Alex on 12.03.2016.
 */
public class CSVReaderTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testReader() {
        String testCSV = "#A\tB\tC\n1\tX\t3\n1\tX\t3\n";
        CSVReader reader = CSVReaderBuilder.create()
                .withHeaderPrefix("#")
                .containsHeader(true)
                .withSeparator('\t').load(testCSV);
        Assert.assertEquals("A", reader.getHeader().get(0));
        Assert.assertEquals("B", reader.getHeader().get(1));
        Assert.assertEquals("C", reader.getHeader().get(2));

        for (CSVRow row : reader) {
            Assert.assertEquals("1", row.get(0));
            Assert.assertEquals("X", row.get(1));
            Assert.assertEquals("3", row.get(2));

            Assert.assertEquals("1", row.get("A"));
            Assert.assertEquals("X", row.get("B"));
            Assert.assertEquals("3", row.get("C"));
        }
    }
}