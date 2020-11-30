package com.example.laugh.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.laugh.util.SHARED_PREFS

object SharedPrefStart {
    fun sharedPrefStart(context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.myEditor(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> myEditor { it.putString(key, value) }
            is Int -> myEditor { it.putInt(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}