package fr.jhelp.tool.tasks

import org.junit.Assert
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class MutexTests
{
    @Test
    fun mutualExclusion()
    {
        val insideMutexCount = AtomicInteger(0)
        val maximumInsideMutexCount = AtomicInteger(0)
        val mutex = Mutex()
        val thread1 = Thread {
            mutex {
                val number = insideMutexCount.incrementAndGet()
                if (number > maximumInsideMutexCount.get())
                {
                    maximumInsideMutexCount.set(number)
                }
                Thread.sleep(1000)
                insideMutexCount.decrementAndGet()
            }
        }
        val thread2 = Thread {
            mutex {
                val number = insideMutexCount.incrementAndGet()
                if (number > maximumInsideMutexCount.get())
                {
                    maximumInsideMutexCount.set(number)
                }
                Thread.sleep(1000)
                insideMutexCount.decrementAndGet()
            }
        }
        val thread3 = Thread {
            mutex {
                val number = insideMutexCount.incrementAndGet()
                if (number > maximumInsideMutexCount.get())
                {
                    maximumInsideMutexCount.set(number)
                }
                Thread.sleep(1000)
                insideMutexCount.decrementAndGet()
            }
        }
        thread1.start()
        thread2.start()
        thread3.start()
        thread1.join()
        thread2.join()
        thread3.join()
        Assert.assertEquals(0, insideMutexCount.get())
        Assert.assertEquals(1, maximumInsideMutexCount.get())
    }
}