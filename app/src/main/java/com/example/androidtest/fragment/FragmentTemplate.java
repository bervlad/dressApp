package com.example.androidtest.fragment;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.listeners.OnLastFragment;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTemplate#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTemplate extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TITLE_ARG = "TITLE_ARG";
    private static final String TEXT_ARG = "TEXT_ARG";
    private static final String IMG_ARG = "IMG_ARG";
    private static final String LAST_ARG = "LAST_ARG";
//    private static final String LOGIN_ARG = "LOGIN_ARG";
//    private static final String REG_ARG = "REG_ARG";


    // TODO: Rename and change types of parameters
    private String title;
    private String text;
    private int img;
    private boolean last;
    private OnLastFragment onLastFragment;


    public FragmentTemplate() {
        // Required empty public constructor
    }

    public static FragmentTemplate newInstance(String title, String text, int img, boolean last) {
        FragmentTemplate fragment = new FragmentTemplate();
        Bundle args = new Bundle();
        args.putString(TITLE_ARG, title);
        args.putString(TEXT_ARG, text);
        args.putInt(IMG_ARG, img);
        args.putBoolean(LAST_ARG, last);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentTemplate newInstance(String text, int img, boolean last) {
        FragmentTemplate fragment = new FragmentTemplate();
        Bundle args = new Bundle();
        args.putString(TEXT_ARG, text);
        args.putInt(IMG_ARG, img);
        args.putBoolean(LAST_ARG, last);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            title = getArguments().getString(TITLE_ARG);
            text = getArguments().getString(TEXT_ARG);
            img = getArguments().getInt(IMG_ARG);
            last = getArguments().getBoolean(LAST_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        TextView textViewTitle, textViewText, textViewSkip, loginTextView, registerTextView, slashTextView;
        ImageView imageView;

        View v = inflater.inflate(R.layout.fragment_template, container, false);

        textViewText = v.findViewById(R.id.fragment_text);
        textViewText.setText(text);

        imageView=v.findViewById(R.id.img);
        imageView.setImageResource(img);

        textViewTitle = v.findViewById(R.id.fragment_title);

        if (last) {
            textViewTitle.setVisibility(View.INVISIBLE);

            textViewSkip=v.findViewById(R.id.fragment_skip);
            textViewSkip.setVisibility(View.VISIBLE);

            loginTextView=v.findViewById(R.id.fragment_login);
            loginTextView.setVisibility(View.VISIBLE);
            loginTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onLastFragment !=null) {
                        onLastFragment.login();
                    }
                }
            });


            registerTextView=v.findViewById(R.id.fragment_register);
            registerTextView.setVisibility(View.VISIBLE);
            registerTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onLastFragment !=null) {
                        onLastFragment.register();
                    }
                }
            });

            slashTextView=v.findViewById(R.id.fragment_slash);
            slashTextView.setVisibility(View.VISIBLE);

            registerTextView.setTextColor(ContextCompat.getColor (Objects.requireNonNull(getContext()), R.color.skip));
            loginTextView.setTextColor(ContextCompat.getColor (getContext(), R.color.skip));

            textViewSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onLastFragment !=null) {
                        onLastFragment.nextActivity();
                    }
                }
            });

        } else {
            textViewTitle.setText(title);
        }

        return v;
    }

    public void setListener (OnLastFragment listener) {
        this.onLastFragment =listener;
    }

}