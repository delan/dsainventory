JC = javac
JFLAGS = -g -Werror

compile:
	$(JC) $(JFLAGS) *.java

run: compile
	java InventoryApplication

clean:
	rm -rfv *.class submission \
		*/*.pdf */*.aux */*.log */*.synctex.gz */*.out

submission: clean report
	mkdir -pv submission
	cd report && pdflatex report.tex
	tar cvzf submission/submission.tar.gz * .git* \
		--exclude=submission --exclude='*.csv' \
		--exclude='*.aux' --exclude='*.log' \
		--exclude='*.out' --exclude='*.synctex.gz'
