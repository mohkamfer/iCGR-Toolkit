package com.mohkamfer.icgrtoolkit.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mohkamfer.icgrtoolkit.R;
import com.mohkamfer.icgrtoolkit.util.ChaosUtil;

import static com.mohkamfer.icgrtoolkit.util.ChaosUtil.Genome.*;

public class EncodeFragment extends Fragment {

    private Button mButton;
    private TextView mResult;
    private TextInputLayout mInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encode, container, false);

        mResult = view.findViewById(R.id.result);
        mInput = view.findViewById(R.id.input_layout);
        mButton = view.findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = mInput.getEditText();
                if (editText != null) {
                    String sequence = editText.getText().toString();
                    if (!sequence.isEmpty()) {
                        processSequence(sequence);
                    }
                }
            }
        });
        return view;
    }

    private void processSequence(String sequence) {
        mResult.setText("");

        if (!ChaosUtil.validSequence(sequence))
            return;

        sequence = sequence.toUpperCase();

        double x = 0d, y = 0d;

        for (int i = 0, length = sequence.length(); i < length; ++i) {
            char current = sequence.charAt(i);
            for (ChaosUtil.Genome genome : ChaosUtil.Genome.values()) {
                if (current == genome.toChar()) {
                    x = ChaosUtil.nextCGREncode(x, genome.x);
                    y = ChaosUtil.nextCGREncode(y, genome.y);
                }
            }

            append(i, x, y);
        }
    }

    private void append(int i, double x, double y) {
        mResult.append(String.format("#%d (%.7f, %.7f)\n", i + 1, x, y));
    }
}
