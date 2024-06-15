package fr.jhelp.tool.tasks

import java.util.concurrent.Semaphore

class Mutex
{
    private val mutex = Semaphore(1, true)

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