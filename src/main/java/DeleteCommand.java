/**
 * The DeleteCommand class, when executed, runs the steps required to delete a Task from a TaskList.
 *
 * @author Jaya Rengam
 */
public class DeleteCommand implements Command {
    private boolean hasExecuted;

    /** The ID in the TaskList of the Task to be deleted. */
    private int taskIdToDelete;

    DeleteCommand(int taskIdToDelete) {
        this.hasExecuted = false;
        this.taskIdToDelete = taskIdToDelete;
    }

    /**
     * Deletes the Task given by taskIdToComplete in the TaskList and updates the Storage text file.
     *
     * @param taskList the TaskList being modified
     * @param ui the Ui object that is used to print the action to the console
     * @param storage the Storage object used to update the text file
     * @throws CartonaException if the command has already been executed
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        // Check if the command has already been executed.
        if (hasExecuted) {
            throw new CartonaException("Error: DeleteCommand already executed");
        }

        // Delete the task
        Task deletedTask = taskList.getTask(taskIdToDelete);
        taskList.deleteTask(taskIdToDelete);

        // Print UI message
        ui.printTaskDeletionMessage(deletedTask, taskList.getSize());

        // Update Storage
        storage.saveListToFile(taskList);

        this.hasExecuted = true;
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
