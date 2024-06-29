package fr.jhelp.tool.tasks

import java.util.concurrent.Semaphore

/**
 * Mutex to protect critical section
 */
class Mutex
{
    /**
     * Semaphore to protect critical section
     */
    private val mutex = Semaphore(1, true)

    /**
     * Execute action in critical section
     *
     * @param action Action to execute
     * @return Action result
     */
    operator fun <T> invoke(action: () -> T): T
    {
        this.mutex.acquire()

        try
        {
            return action()
        }
        finally
        {
            this.mutex.release()
        }
    }
}