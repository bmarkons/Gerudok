package gerudok.filters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DocumentFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		return (f.isDirectory() || f.getName().toLowerCase().endsWith(".gdoc"));
	}

	@Override
	public String getDescription() {
		return "Gerudok Document Files (*.gdoc)";
	}

}
