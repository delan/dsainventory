JC = javac
JFLAGS = -g -Werror

compile:
	$(JC) $(JFLAGS) *.java

run: compile
	java InventoryApplication

clean:
	rm -rfv *.class submission */*.aux */*.log */*.synctex.gz */*.out

submission: clean
	mkdir -pv submission
	tar cvzf submission/submission.tar.gz * .git* \
		--exclude=submission --exclude='*.csv'
