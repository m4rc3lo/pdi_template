package br.univali.pdi;
import org.junit.Test;
import static org.junit.Assert.*;
public class CliParserTest {
 @Test public void parsesAndValidatesKnownOperation(){
   CliOptions o=CliParser.parse(new String[]{"--operation","negative","--input","in.png","--output","out.png"});
   assertEquals("negative",o.operation);
   assertTrue(Operations.isKnown(o.operation));
   assertTrue(Contract.validate(o).ok());
   assertFalse(Operations.isKnown("x"));
 }
}
