	package dal;

import bo.SectionTest;
import bo.Theme;

public interface DAOSectionTest extends DAOGeneric<SectionTest> {
	public Theme getThemeViaIdTest(int idTest) throws DALException;
	public SectionTest getSectionTestViaIdThemeAndIdTest(int idTest, int idTheme) throws DALException;
}
