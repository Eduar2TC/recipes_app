
import android.content.Context
import android.content.res.Configuration
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import com.example.recipesfood.R

class CustomPopup(private val context: Context) {

    private val popupView: View = LayoutInflater.from(context).inflate(R.layout.custom_popup, null)
    private val popupWindow = PopupWindow(
        popupView,
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT,
        true
    )

    init {
        setupPopupWindow()
        setupTransitions()
        setupTheme()
    }

    private fun setupPopupWindow() {
        popupWindow.elevation = 20.0F
        popupWindow.width = 600
        popupWindow.isFocusable = true
        popupWindow.setBackgroundDrawable(
            ContextCompat.getDrawable(context, R.drawable.popup_background)
        )
    }
    private fun setupTransitions() {
        val enterTransitionSet = TransitionSet()
        val exitTransitionSet = TransitionSet()
        //animate from top right to bottom left

        val slideEnter = Slide(Gravity.TOP)
        slideEnter.duration = 500
        val fadeEnter = Fade()
        fadeEnter.duration = 300
        enterTransitionSet.addTransition(slideEnter)
        enterTransitionSet.addTransition(fadeEnter)

        val slideExit = Slide(Gravity.TOP)
        val fadeExit = Fade()
        slideExit.duration = 200
        fadeExit.duration = 100
        exitTransitionSet.addTransition(slideExit)

        popupWindow.enterTransition = enterTransitionSet
        popupWindow.exitTransition = exitTransitionSet
    }

    fun show(anchor: View) {
        setupMenuItems()
        popupWindow.showAsDropDown(anchor, 0, 27, Gravity.RIGHT)
    }

    private fun setupMenuItems() {
        // Configuración de los elementos del menú
        val iconImageView: ImageView = popupView.findViewById(R.id.icon)
        val titleTextView: TextView = popupView.findViewById(R.id.title)

        // Configura los elementos según tus necesidades
        // Por ejemplo:
        // iconImageView.setImageResource(R.drawable.your_icon)
        // titleTextView.text = "Your Title"

        popupView.findViewById<TextView>(R.id.notifications).setOnClickListener {
            showToast("Item 2 clicked")
        }
        popupView.findViewById<TextView>(R.id.settings).setOnClickListener {
            showToast("Item 3 clicked")
        }

    }
    private fun setupTheme() {
        val isNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

        val switchButton = popupView.findViewById<SwitchCompat>(R.id.switchButton)
        switchButton.isChecked = isNightMode

        switchButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
