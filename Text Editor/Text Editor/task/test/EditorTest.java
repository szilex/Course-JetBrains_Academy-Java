import editor.TextEditor;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JMenuItemFixture;
import org.assertj.swing.fixture.JScrollPaneFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.hyperskill.hstest.v6.stage.SwingTest;
import org.hyperskill.hstest.v6.testcase.CheckResult;
import org.hyperskill.hstest.v6.testcase.TestCase;
import org.junit.After;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;


class TestClue {

    public Supplier<Boolean> function;
    public String feedback;

    public TestClue(String feedback, Supplier<Boolean> function) {
        this.function = function;
        this.feedback = feedback;
    }

}


public class EditorTest extends SwingTest<TestClue> {

    public EditorTest() throws Exception {
        super(new TextEditor());
    }

    private JTextComponentFixture textArea;
    private JTextComponentFixture filenameField;
    private JButtonFixture saveButton;
    private JButtonFixture loadButton;
    private JScrollPaneFixture scrollPane;
    private JMenuItemFixture menuFile;
    private JMenuItemFixture menuLoad;
    private JMenuItemFixture menuSave;
    private JMenuItemFixture menuExit;

    String filename1 = "SomeFile.txt";
    String filename2 = "AnotherFile.txt";
    String noExistFile = "FileDoesNotExist";

    String textToSave1 = "Basic text editor\nType here too\nHere also\n\n";
    String textToSave2 = "                Sonnet I\n" +
        "     \n" +
        "     \n" +
        "FROM fairest creatures we desire increase,\n" +
        "That thereby beauty's rose might never die,\n" +
        "But as the riper should by time decease,\n" +
        "His tender heir might bear his memory:\n" +
        "But thou, contracted to thine own bright eyes,\n" +
        "Feed'st thy light'st flame with self-substantial fuel,\n" +
        "Making a famine where abundance lies,\n" +
        "Thyself thy foe, to thy sweet self too cruel.\n" +
        "Thou that art now the world's fresh ornament\n" +
        "And only herald to the gaudy spring,\n" +
        "Within thine own bud buriest thy content\n" +
        "And, tender churl, makest waste in niggarding.\n" +
        "Pity the world, or else this glutton be,\n" +
        "To eat the world's due, by the grave and thee.\n" +
        "\n" +
        "                 Sonnet II                   \n" +
        "\n" +
        "\n" +
        "When forty winters shall beseige thy brow,\n" +
        "And dig deep trenches in thy beauty's field,\n" +
        "Thy youth's proud livery, so gazed on now,\n" +
        "Will be a tatter'd weed, of small worth held:\n" +
        "Then being ask'd where all thy beauty lies,\n" +
        "Where all the treasure of thy lusty days,\n" +
        "To say, within thine own deep-sunken eyes,\n" +
        "Were an all-eating shame and thriftless praise.\n" +
        "How much more praise deserved thy beauty's use,\n" +
        "If thou couldst answer 'This fair child of mine\n" +
        "Shall sum my count and make my old excuse,'\n" +
        "Proving his beauty by succession thine!\n" +
        "This were to be new made when thou art old,\n" +
        "And see thy blood warm when thou feel'st it cold.";


    @Override
    public List<TestCase<TestClue>> generate() {
        return List.of(

            // general tests

            new TestCase<TestClue>().setAttach(new TestClue(
                "Title is empty",
                () -> frame.getTitle().length() > 0)),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Window is not visible",
                () -> frame.isVisible())),


            // existence tests

            new TestCase<TestClue>().setAttach(new TestClue(
                "There is no text component with name TextArea",
                () -> checkExistence(() -> {
                    textArea = window.textBox("TextArea");
                    return textArea;
                }))),

            new TestCase<TestClue>().setAttach(new TestClue(
                "There is no text component with name FilenameField",
                () -> checkExistence(() -> {
                    filenameField = window.textBox("FilenameField");
                    return filenameField;
                }))),

            new TestCase<TestClue>().setAttach(new TestClue(
                "There is no button with name SaveButton",
                () -> checkExistence(() -> {
                    saveButton = window.button("SaveButton");
                    return saveButton;
                }))),

            new TestCase<TestClue>().setAttach(new TestClue(
                "There is no button with name LoadButton",
                () -> checkExistence(() -> {
                    loadButton = window.button("LoadButton");
                    return loadButton;
                }))),

            new TestCase<TestClue>().setAttach(new TestClue(
                "There is no scroll component with name ScrollPane",
                () -> checkExistence(() -> {
                    scrollPane = window.scrollPane("ScrollPane");
                    return scrollPane;
                }))),

            new TestCase<TestClue>().setAttach(new TestClue(
                "There is no menu option with name MenuFile",
                () -> checkExistence(() -> {
                    menuFile = window.menuItem("MenuFile");
                    return menuFile;
                }))),

            new TestCase<TestClue>().setAttach(new TestClue(
                "There is no menu option with name MenuLoad",
                () -> checkExistence(() -> {
                    menuLoad = window.menuItem("MenuLoad");
                    return menuLoad;
                }))),

            new TestCase<TestClue>().setAttach(new TestClue(
                "There is no menu option with name MenuSave",
                () -> checkExistence(() -> {
                    menuSave = window.menuItem("MenuSave");
                    return menuSave;
                }))),

            new TestCase<TestClue>().setAttach(new TestClue(
                "There is no menu option with name MenuExit",
                () -> checkExistence(() -> {
                    menuExit = window.menuItem("MenuExit");
                    return menuExit;
                }))),



            // logic tests

            new TestCase<TestClue>().setAttach(new TestClue(
                "TextArea should be editable",
                () -> {
                    textArea.requireEditable();
                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "TextArea should be empty at the start of the program",
                () -> {
                    textArea.requireEmpty();
                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "FilenameField should be empty at the start of the program",
                () -> {
                    filenameField.requireEmpty();
                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "SaveButton should be enabled for clicking",
                () -> saveButton.isEnabled())),

            new TestCase<TestClue>().setAttach(new TestClue(
                "LoadButton should be enabled for clicking",
                () -> loadButton.isEnabled())),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Can't enter multiline text in TextArea.",
                () -> {
                    textArea.setText(textToSave1);
                    textArea.requireText(textToSave1);
                    textArea.setText("");
                    textArea.setText(textToSave2);
                    textArea.requireText(textToSave2);
                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Can enter multiline text in FilenameField, but shouldn't",
                () -> {
                    String text = textToSave1;
                    filenameField.setText(text);
                    filenameField.requireText(text.replace("\n", " "));
                    filenameField.setText("");
                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Text in FilenameField and in TextArea " +
                    "should stay the same after saving file",
                () -> {
                    filenameField.setText(filename1);
                    textArea.setText(textToSave1);

                    saveButton.click();

                    filenameField.requireText(filename1);
                    textArea.requireText(textToSave1);

                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Text in FilenameField and in TextArea " +
                    "should stay the same after saving file",
                () -> {
                    String text = textToSave2;
                    String file = filename2;

                    filenameField.setText(file);
                    textArea.setText(text);

                    saveButton.click();

                    filenameField.requireText(file);
                    textArea.requireText(text);

                    filenameField.setText("");
                    textArea.setText("");

                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Text in FilenameField stay the same after loading file",
                () -> {
                    String file = filename1;

                    filenameField.setText(file);
                    textArea.setText("");

                    loadButton.click();

                    filenameField.requireText(file);

                    filenameField.setText("");
                    textArea.setText("");

                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Text should be the same after saving and loading same file",
                () -> {
                    String[] texts = {textToSave2, textToSave1};
                    String[] files = {filename1, filename2};

                    for (int i = 0; i < 2; i++) {

                        String text = texts[i];
                        String file = files[i];

                        filenameField.setText("");
                        textArea.setText("");

                        filenameField.setText(file);
                        textArea.setText(text);

                        saveButton.click();

                        filenameField.setText("");
                        textArea.setText("");

                        filenameField.setText(file);
                        loadButton.click();

                        textArea.requireText(text);
                    }

                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "TextArea should be empty if user tries to " +
                    "load file that doesn't exist",
                () -> {

                    textArea.setText(textToSave1);
                    filenameField.setText(noExistFile);

                    loadButton.click();
                    textArea.requireText("");

                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "TextArea should correctly save and load an empty file",
                () -> {
                    textArea.setText("");
                    filenameField.setText(filename1);

                    saveButton.click();
                    textArea.setText(textToSave2);
                    loadButton.click();
                    textArea.requireText("");

                    return true;
                })),



            // menu-related tests

            new TestCase<TestClue>().setAttach(new TestClue(
                "MenuLoad should be enabled for clicking",
                () -> {
                    menuLoad.requireEnabled();
                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "MenuSave should be enabled for clicking",
                () -> {
                    menuSave.requireEnabled();
                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "MenuFile should be enabled for clicking",
                () -> {
                    menuFile.requireEnabled();
                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "MenuExit should be enabled for clicking",
                () -> {
                    menuExit.requireEnabled();
                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Text in FilenameField and in TextArea " +
                    "should stay the same after saving file using MenuSave",
                () -> {
                    filenameField.setText(filename1);
                    textArea.setText(textToSave1);

                    menuSave.click();

                    filenameField.requireText(filename1);
                    textArea.requireText(textToSave1);

                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Text in FilenameField and in TextArea " +
                    "should stay the same after saving file using MenuSave",
                () -> {
                    String text = textToSave2;
                    String file = filename2;

                    filenameField.setText(file);
                    textArea.setText(text);

                    menuSave.click();

                    filenameField.requireText(file);
                    textArea.requireText(text);

                    filenameField.setText("");
                    textArea.setText("");

                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Text in FilenameField stay " +
                    "the same after loading file using MenuLoad",
                () -> {
                    String file = filename1;

                    filenameField.setText(file);
                    textArea.setText("");

                    menuLoad.click();

                    filenameField.requireText(file);

                    filenameField.setText("");
                    textArea.setText("");

                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "Text should be the same after saving " +
                    "and loading same file using MenuLoad",
                () -> {
                    String[] texts = {textToSave2, textToSave1};
                    String[] files = {filename1, filename2};

                    for (int i = 0; i < 2; i++) {

                        String text = texts[i];
                        String file = files[i];

                        filenameField.setText("");
                        textArea.setText("");

                        filenameField.setText(file);
                        textArea.setText(text);

                        menuSave.click();

                        filenameField.setText("");
                        textArea.setText("");

                        filenameField.setText(file);
                        menuLoad.click();

                        textArea.requireText(text);
                    }

                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "TextArea should be empty if user tries to " +
                    "load file that doesn't exist using MenuLoad",
                () -> {

                    textArea.setText(textToSave1);
                    filenameField.setText(noExistFile);

                    menuLoad.click();
                    textArea.requireText("");

                    return true;
                })),

            new TestCase<TestClue>().setAttach(new TestClue(
                "TextArea should correctly save and load an empty file using menu",
                () -> {
                    textArea.setText("");
                    filenameField.setText(filename1);

                    menuSave.click();
                    textArea.setText(textToSave2);
                    menuLoad.click();
                    textArea.requireText("");
                    return true;
                }))
        );
    }

    @Override
    public CheckResult check(String reply, TestClue clue) {
        try {
            return new CheckResult(clue.function.get(), clue.feedback);
        }
        catch (AssertionError ex) {
            return new CheckResult(false, clue.feedback);
        }
    }

    @After
    public void deleteFiles() {
        try {
            Files.delete(Paths.get(filename1));
            Files.delete(Paths.get(filename2));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
