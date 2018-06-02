package subscription.action

class Action<T> {
    private var mSubscribers: MutableList<ActionListener<T>>
    private var capacity: Int = -1

    /**
     * TRUE if the capacity is full if you specific the capacity when constructing.
     */
    var isFull: Boolean = false
        get() {
            return when {
                capacity == -1 -> false
                mSubscribers.size >= this.capacity -> true
                else -> false
            }
        }
        private set

    constructor() {
        mSubscribers = ArrayList()
        this.capacity = -1
    }

    constructor(capacity: Int) {
        this.capacity = capacity
        mSubscribers = ArrayList(capacity)
    }

    /**
     * publish the notification message.
     */
    fun notify(message: T) {
        mSubscribers.forEach {
            it.notifyMessage(message)
        }
    }

    operator fun plusAssign(listener: ActionListener<T>) {
        if (capacity != -1 && mSubscribers.size >= capacity) {
            print(IllegalStateException("Capacity id full, this subscription is avoid"))
            return
        }
        if (listener != null && mSubscribers.contains(listener)) {
            mSubscribers.remove(listener)
        }
        mSubscribers.add(listener)
    }

    operator fun minusAssign(listener: ActionListener<T>) {
        if (listener != null && mSubscribers.contains(listener)) {
            mSubscribers.remove(listener)
        }
    }
}

/**
 * Listener for action.
 */
interface ActionListener<in T> {
    fun notifyMessage(msg: T)
}