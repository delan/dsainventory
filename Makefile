JC = javac
JFLAGS = -g -Werror

compile:
	$(JC) $(JFLAGS) *.java

run: compile
	java InventoryApplication

clean:
	rm -rfv *.class submission \
		*/*.pdf */*.aux */*.log */*.synctex.gz */*.out

genreport:
	cd report && pdflatex report.tex

submission: clean genreport
	mkdir -pv submission
	tar cvzf submission/submission.tar.gz * .git* \
		--exclude=submission --exclude='*.csv' \
		--exclude='*.aux' --exclude='*.log' \
		--exclude='*.out' --exclude='*.synctex.gz'
