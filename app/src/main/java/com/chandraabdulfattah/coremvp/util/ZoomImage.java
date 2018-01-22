package com.chandraabdulfattah.coremvp.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by bezzo on 22/12/17.
 */

public class ZoomImage {

    private Animator mCurrentAnimatorEffect;
    private int mShortAnimationDurationEffect;

    public ZoomImage(){

    }

    public void show(final Animator currentAnimatorEffect, final View thumbView, Context context,
                          String urlPhoto, final ImageView ivExpandedPhoto, View container,
                          final int shortAnimationDurationEffect){
        this.mCurrentAnimatorEffect = currentAnimatorEffect;
        this.mShortAnimationDurationEffect = shortAnimationDurationEffect;

        if (mCurrentAnimatorEffect != null) mCurrentAnimatorEffect.cancel();

        Glide.with(context).load(urlPhoto)
                .apply(CommonUtils.requestImageHandler())
                .into(ivExpandedPhoto);

        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        thumbView.getGlobalVisibleRect(startBounds);
        container.getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        thumbView.setAlpha(0f);
        ivExpandedPhoto.setVisibility(View.VISIBLE);

        ivExpandedPhoto.setPivotX(0f);
        ivExpandedPhoto.setPivotY(0f);

        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(ivExpandedPhoto, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(ivExpandedPhoto, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(ivExpandedPhoto, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(ivExpandedPhoto,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDurationEffect);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimatorEffect = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimatorEffect = null;
            }
        });
        set.start();
        mCurrentAnimatorEffect = set;

        final float startScaleFinal = startScale;
        ivExpandedPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimatorEffect != null) {
                    mCurrentAnimatorEffect.cancel();
                }

                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(ivExpandedPhoto, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(ivExpandedPhoto,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(ivExpandedPhoto,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(ivExpandedPhoto,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDurationEffect);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        ivExpandedPhoto.setVisibility(View.GONE);
                        mCurrentAnimatorEffect = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        ivExpandedPhoto.setVisibility(View.GONE);
                        mCurrentAnimatorEffect = null;
                    }
                });
                set.start();
                mCurrentAnimatorEffect = set;
            }
        });
    }
}
